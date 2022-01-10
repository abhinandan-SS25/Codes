from django.contrib.auth import authenticate, login, logout
from django.db import IntegrityError
from django.http import HttpResponse, HttpResponseRedirect
from django.http.response import JsonResponse
from django.shortcuts import render
from django.urls import reverse
import django.utils.encoding 
from django.core.paginator import Paginator
from django.utils.functional import SimpleLazyObject
from django.db.models import Q

from .models import *
from django.views.decorators.csrf import csrf_exempt
import json

@csrf_exempt #could not figure out how to include the csrf token in the post body through fetch and thus failed authentication and subsequently removed the requirement.
def index(request):
    if request.method=="POST":

        #post related operations-save edited posts and like posts
        try:
            p_data=json.loads(request.body)
            #save edited post
            if p_data["operation"] == "save_edited_post":
                Post.objects.filter(id=p_data["postid"]).update(post_body=p_data["postbody"])
                print("Data updated")
            #like post and update like count
            elif p_data["operation"] == "like_post":
                pl=Post.objects.get(id=p_data["postid"])
                ll=Like(post_desc=pl,liked_by=request.user)
                ll.save()
                total_likes=Like.objects.filter(post_desc=pl).count()
                return JsonResponse({"total_likes":total_likes})
            elif p_data["operation"] == "unlike_post":
                p=Post.objects.get(id=p_data["postid"])
                l=Like.objects.filter(post_desc=p,liked_by=request.user)
                l.delete()
                total_likes=Like.objects.filter(post_desc=p).count()
                return JsonResponse({"total_likes":total_likes})
        except:
            pass

        #Save new post
        try:
            c_user=User.objects.get(username=request.user)
            n_post=Post(post_by=c_user, post_body=request.POST["post_body"])
            n_post.save()
            print("Saved new post")
        except:
            pass
    

    #like count and update accordingly
    likes=Like.objects.all()
    posts=Post.objects.all()
    post_likes=[]
    for post in posts:
        l=0
        l_b=[]
        for like in likes:
            if post.id == like.post_desc.id :
                l=l+1
                l_b.append(like.liked_by)

        if django.utils.encoding.force_str(request.user, encoding='utf-8', strings_only=False, errors='strict') in l_b:
            post_likes.append({"id":post.id,"total_likes":l,"liked":True}) 
        else:
            post_likes.append({"id":post.id,"total_likes":l,"liked":False})  
     
    #GET request render
    paginator = Paginator(Post.objects.order_by("post_time").reverse(), 10) # Show 10 contacts per page.
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)
    return render(request, "network/index.html",{
        "page_obj":page_obj,
        "current_u":request.user,
        "likes":post_likes
    })


def all_posts(request):
    likes=Like.objects.all()
    posts=Post.objects.all()
    post_likes=[]
    for post in posts:
        l=0
        l_b=[]
        for like in likes:
            if post.id == like.post_desc.id :
                l=l+1
                l_b.append(like.liked_by)

        if django.utils.encoding.force_str(request.user, encoding='utf-8', strings_only=False, errors='strict') in l_b:
            post_likes.append({"id":post.id,"total_likes":l,"liked":True}) 
        else:
            post_likes.append({"id":post.id,"total_likes":l,"liked":False})
    paginator = Paginator(Post.objects.order_by("post_time").reverse(), 10) # Show 10 contacts per page.
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)
    return render(request, "network/all_posts.html",{
        "page_obj":page_obj,
        "current_u":request.user,
        "likes":post_likes})


def following_posts(request):
    following=Follow.objects.filter(user_account=request.user)
    likes=Like.objects.all()
    posts=Post.objects.all()
    post_likes=[]
    for post in posts:
        l=0
        l_b=[]
        for like in likes:
            if post.id == like.post_desc.id :
                l=l+1
                l_b.append(like.liked_by)

        if django.utils.encoding.force_str(request.user, encoding='utf-8', strings_only=False, errors='strict') in l_b:
            post_likes.append({"id":post.id,"total_likes":l,"liked":True}) 
        else:
            post_likes.append({"id":post.id,"total_likes":l,"liked":False})

    if len(following)==0:
        has_f=False
    else:
        has_f=True
    u_query=Q()
    for u in following:
        u_query.add(Q(username=u.following_account), Q.OR)
    p=User.objects.filter(u_query)
    p_query=Q()
    for r in p:
        p_query.add(Q(post_by=r), Q.OR)

    paginator = Paginator(Post.objects.filter(p_query).order_by("post_time").reverse(), 10) # Show 10 contacts per page.
    page_number = request.GET.get('page')
    page_obj = paginator.get_page(page_number)
    return render(request, "network/following.html",{
        "page_obj":page_obj,
        "current_u":request.user,
        "likes":post_likes,
        "has_f":has_f
        })


@csrf_exempt
def profile_view(request):

    if request.method=="POST":
        f_data=json.loads(request.body)
        if f_data["operation"]=="follow":
            Follow(user_account=request.user,following_account=f_data["account"]).save()
            print("Followed new user")
            total_followers=Follow.objects.filter(following_account=f_data["account"]).count()
            return JsonResponse({"total_followers":total_followers})
        else:
            Follow.objects.filter(user_account=request.user,following_account=f_data["account"]).delete()
            print("Unollowed user")
            total_followers=Follow.objects.filter(following_account=f_data["account"]).count()
            return JsonResponse({"total_followers":total_followers})

    username=request.GET["profile_name"]
    posts_user=Post.objects.all().filter(post_by=User.objects.get(username=username))

    likes=Like.objects.all()
    posts=Post.objects.filter(post_by=User.objects.get(username=request.GET["profile_name"]))
    post_likes=[]
    for post in posts:
        l=0
        l_b=[]
        for like in likes:
            if post.id == like.post_desc.id :
                l=l+1
                l_b.append(like.liked_by)

        if django.utils.encoding.force_str(request.user, encoding='utf-8', strings_only=False, errors='strict') in l_b:
            post_likes.append({"id":post.id,"total_likes":l,"liked":True}) 
        else:
            post_likes.append({"id":post.id,"total_likes":l,"liked":False})

    if Follow.objects.filter(user_account=request.user,following_account=username).exists():
        follows=True
    else:
        follows=False

    return render(request, "network/view_profile.html", {
        "posts_user":posts_user,
        "username":username,
        "current_u":request.user,
        "c":django.utils.encoding.force_str(request.user, encoding='utf-8', strings_only=False, errors='strict'),
        "likes":post_likes,
        "total_following":Follow.objects.filter(user_account=username).count(),
        "total_followers":Follow.objects.filter(following_account=username).count(),
        "follows":follows
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
            return render(request, "network/login.html", {
                "message": "Invalid username and/or password."
            })
    else:
        return render(request, "network/login.html")


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
            return render(request, "network/register.html", {
                "message": "Passwords must match."
            })

        # Attempt to create new user
        try:
            user = User.objects.create_user(username, email, password)
            user.save()
        except IntegrityError:
            return render(request, "network/register.html", {
                "message": "Username already taken."
            })
        login(request, user)
        return HttpResponseRedirect(reverse("index"))
    else:
        return render(request, "network/register.html")