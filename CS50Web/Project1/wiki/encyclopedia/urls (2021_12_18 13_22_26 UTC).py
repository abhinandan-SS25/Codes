from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("<str:title>",views.TITLE, name="TITLE"),
    path("index/newpage.html", views.newpage, name="newpage"),
    path("index/editpage.html", views.editpage, name="editpage"),
    path("index/randpage.html", views.randpage, name="randpage")

]
