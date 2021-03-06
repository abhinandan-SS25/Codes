from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("login", views.login_view, name="login"),
    path("logout", views.logout_view, name="logout"),
    path("register", views.register, name="register"),
    path("watchlist", views.watchlist, name="watchlist"),
    path("categories", views.categories, name="categories"),
    path("LISTING", views.LISTING, name="listingsite"),
    path("categories/CATEGORY", views.CATEGORY, name="category_type"),
    path("create_listing", views.create_listing, name="create_listing")
]
