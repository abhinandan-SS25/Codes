from django.contrib.auth import authenticate, login, logout
from django.db import IntegrityError
from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
from django.urls import reverse

from .models import *


def index(request):
    return render(request, "auctions/index.html",{
        "items":Listing.objects.all(),
    })


def categories(request):
    return render(request, "auctions/categories.html",{
        "ctgs":Category.objects.all()
    })


def watchlist(request):
    return render(request,"auctions/watchlist.html",{
        "watching":Watchlist.objects.all().filter(u_name=request.user),
        "items":Listing.objects.all()
    })


def CATEGORY(request):
    category_name=str(request.GET["category_title"])
    return render(request, "auctions/CATEGORY.html",{
        "itms":Listing.objects.all(),
        "c_name": category_name
    })


def LISTING(request):
    
    if request.method=="POST":

        message=""

        #close listing
        try:
            n=Listing.objects.get(title=request.POST["listing_name"])
            n.status=request.POST["status_change"]
            n.save()
            message="Listing closed.... "+Listing.objects.get(title=request.POST["listing_name"]).c_bidder+"Wins!"
        except:
            pass
        
        #new bid
        try: 
            if Listing.objects.get(title=request.POST["listing_name"]).status=="A":

                n_bid=request.POST["new_bid"]
                if float(n_bid)>Listing.objects.get(title=request.POST["listing_name"]).c_price:
                    g=Listing.objects.get(title=request.POST["listing_name"])
                    g.c_price=n_bid
                    g.c_bidder=str(request.user)
                    g.save()
                    message=f"New bid at {g.c_price} by {g.c_bidder}"
                else:
                    message="Bid doesn't meet bid requirements"
        except:
            pass
        
        #new comment
        try:
            Comment(listing=request.POST["listing_name"],usern=request.user,comment=request.POST["new_comment"]).save()
            message="New comment"
        except:
            pass
        
        #add to watchlist
        try:
            i_ac=request.POST["listing_name"]
            ac=request.POST["Todo"]
            if ac=="Add To Watchlist":
                Watchlist(u_name=request.user,item_name=i_ac).save()
                message="Added to Watchlist"
            else:
                ri=Watchlist.objects.all().filter(u_name=request.user).filter(item_name=i_ac).delete()
                message="Removed from Watchlist"

            

        except:
            pass

        #page render for POST, with updated info
        us=request.user
        w=Watchlist.objects.all().filter(u_name=request.user)
        t="Add To Watchlist"
        for e in w:
            if request.POST["listing_name"]==e.item_name:
                t="Remove From Watchlist"
                break
            else:
                t="Add To Watchlist"
        return render(request, "auctions/LISTING.html", {
        "listitem":Listing.objects.get(title=request.POST["listing_name"]),
        "c_user":request.user,
        "comnts":Comment.objects.all().filter(listing=request.POST["listing_name"]),
        "message":message,
        "but_msg":t,
        "wa":w,
        "use":us
    })

    #page render for GET, with updated info
    us=request.user
    l_title=request.GET["l_name"]
    w=Watchlist.objects.all().filter(u_name=request.user)
    for e in w:
        if l_title==e.item_name:
            t="Remove From Watchlist"
            break
    else:
        t="Add To Watchlist"
    return render(request, "auctions/LISTING.html", {
        "listitem":Listing.objects.get(title=l_title),
        "c_user":request.user,
        "comnts":Comment.objects.all().filter(listing=l_title),
        "but_msg":t,
        "wa":w,
        "use":us
    })


def create_listing(request):
    if request.method=="POST":
        if request.POST["n_category"] != "":
            print(request.POST["n_category"])
            Category(category=request.POST["n_category"]).save()
            c=Category.objects.get(category=request.POST["n_category"])
        else:
            c=Category.objects.get(category=request.POST["cg"])
        if request.POST["pic"]=="":
            p="https://cdn0.iconfinder.com/data/icons/technology-business-and-people/1000/file_light-15-512.png"
        else:
            p=request.POST["pic"]
        Listing(username=request.user,status="A",title=request.POST["ttl"],listing_open_date=datetime.datetime.now().date(), description=request.POST["desc"], l_price=request.POST["l_p"], c_price=request.POST["l_p"], c_bidder=request.user, category=c, picture=p).save()
        return render(request, "auctions/create_listing.html",{
        "catgs": Category.objects.all(),
        "message":"Listing Created"
    })
    return render(request, "auctions/create_listing.html",{
        "catgs": Category.objects.all()
    })


def login_view(request):
    if request.method == "POST":

        # Attempt to sign user in
        username = request.POST["username"]
        password = request.POST["password"]
        user = authenticate(request, username=username, password=password)

        # Check if authentication successful
        if user is not None:
            login(request, user)
            return HttpResponseRedirect(reverse("index"))
        else:
            return render(request, "auctions/login.html", {
                "message": "Invalid username and/or password."
            })
    else:
        return render(request, "auctions/login.html")


def logout_view(request):
    logout(request)
    return HttpResponseRedirect(reverse("index"))


def register(request):
    if request.method == "POST":
        username = request.POST["username"]
        email = request.POST["email"]

        # Ensure password matches confirmation
        password = request.POST["password"]
        confirmation = request.POST["confirmation"]
        if password != confirmation:
            return render(request, "auctions/register.html", {
                "message": "Passwords must match."
            })

        # Attempt to create new user
        try:
            user = User.objects.create_user(username, email, password)
            user.save()
        except IntegrityError:
            return render(request, "auctions/register.html", {
                "message": "Username already taken."
            })
        login(request, user)
        return HttpResponseRedirect(reverse("index"))
    else:
        return render(request, "auctions/register.html")
