from django.contrib.auth.models import AbstractUser
from django.db import models
from django.db.models.enums import Choices
from django.db.models.fields import CharField
from django.db.models.fields.related import ForeignKey
import datetime


class User(AbstractUser):
    pass


#Class for sorting into categories
class Categories(models.Model):
    category=models.CharField(max_length=60)    
    def __str__(self):
        return f"   Category-Name : {self.category}"#all categories
    
    
#Class for listings data in db
class Listings(models.Model):
    status=models.CharField(choices=(("A","ACTIVE"),("C","CLOSE")),max_length=1)    #Status for the listing...close/active
    title=models.CharField(max_length=60)   #title for the listing
    listing_open_date=models.DateField(default=datetime.datetime.now().date())  #to get date of listing
    description=models.TextField()  #description for the listing
    l_price=models.FloatField() #The initial listing/bidding price
    c_price=models.FloatField(blank=True,null=True) #the current price for the listing
    category=models.ForeignKey(Categories,on_delete=models.CASCADE,related_name="Type")    #category for the listing 
    picture=models.URLField(blank=True,null=True) #image to be provided for the listing
    def __str__(self):
        return f"   Name : {self.title};  Category : {self.category}"
    