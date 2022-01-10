from django.contrib.auth.models import AbstractUser
from django.db import models


class User(AbstractUser):
    pass

class Post(models.Model):
    post_by=models.ForeignKey(User, on_delete=models.CASCADE)
    post_body=models.CharField(max_length=2000)
    post_time=models.DateTimeField(auto_now_add=True)

class Like(models.Model):
    post_desc=models.ForeignKey(Post, on_delete=models.CASCADE)
    liked_by=models.CharField(max_length=400)

class Follow(models.Model):
    user_account=models.CharField(max_length=400)
    following_account=models.CharField(max_length=400)