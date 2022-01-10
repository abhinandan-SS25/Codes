from django.http.response import HttpResponse, HttpResponseRedirect
from django.shortcuts import render
import random
import markdown2
from . import util


def index(request):
    return render(request, "encyclopedia/index.html", {
        "entries": util.list_entries()
    })

def newpage(request):
    if request.method=="POST":
        if request.POST.get("edit?")=="This is an edit":
            e_t=request.POST.get("title1")
            util.save_entry(e_t,request.POST.get("content"))
            return render(request, "encyclopedia/TITLE.html", {
            "entry":markdown2.markdown(util.get_entry(e_t)),
            "title":e_t})

        n_t=request.POST.get("title")
        n_c=request.POST.get("content")
        if n_t in util.list_entries():
            return render(request, "encyclopedia/TITLE.html", {
                "e_m":0,
                "name":n_t
                })
        util.save_entry(n_t,n_c)
        return render(request, "encyclopedia/TITLE.html", {
        "entry":markdown2.markdown(util.get_entry(n_t)),
        "title":n_t
    })
    return render(request, "encyclopedia/newpage.html", {
    })

def editpage(request):
    e_t=request.POST.get("title")
    return render(request, "encyclopedia/editpage.html", {
        "entry":util.get_entry(e_t),
        "title":e_t
        })

def TITLE(request,title):
    if request.method=="POST":
        t=request.POST.get("q")
        try:
            return render(request, "encyclopedia/TITLE.html", {
            "entry":markdown2.markdown(util.get_entry(t)),
            "title":t,
            "pages": util.list_entries()
            })  
        except:
            return render(request, "encyclopedia/TITLE.html", {
            "entry":util.get_entry(t),
            "title":t,
            "pages": util.list_entries()
            })  
    return render(request, "encyclopedia/TITLE.html", {
        "entry":markdown2.markdown(util.get_entry(title)),
        "title":title
    })

def randpage(request):
    a_p=util.list_entries()
    page=random.choice(a_p)
    return render(request, "encyclopedia/TITLE.html", {
        "entry":markdown2.markdown(util.get_entry(page)),
        "title":page
    })
