from django.db import models
from django.contrib.auth.models import User
# Create your models here.

class listingmodels(models.Model):
    user = models.ForeignKey(User, on_delete = models.CASCADE)
    works = models.CharField(max_length=50,null=False, default='')
    
    def __str__(self):
        return self.works
        
