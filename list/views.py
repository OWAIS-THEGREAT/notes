from django.shortcuts import render
from django.http import HttpResponse
from django.contrib.auth.models import User
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from rest_framework.authtoken.models import Token
from rest_framework.permissions import AllowAny
from django.contrib.auth import authenticate,login,logout
from .models import listingmodels
# Create your views here.
def home(request):
    return HttpResponse('hello world')

@api_view(['POST'])
@permission_classes([AllowAny])
def signout(request):
    if request.method=='POST':
        logout(request)
        return Response({'message':'logged out successfull'})


@api_view(['POST'])
@permission_classes([AllowAny])
def signup(request):
    
    if request.method=='POST':
        username = request.POST['username']
        email = request.POST['email']
        pass1 = request.POST['pass1']
        pass2 = request.POST['pass2']
        
        if pass1==pass2:
            user = User.objects.create_user(username=username,email=email,password=pass1)
            user.save()
            
            token, _ = Token.objects.get_or_create(user=user)
            userdata = {
                'username':user.username
            }
            return Response(userdata)
        
        else:
            return Response({'message':'password does not match'})
    else:
        return Response({'message':'invalid request'})
        
        
@api_view(['POST'])
@permission_classes([AllowAny])
def signin(request):
    if request.method=='POST':
        username = request.POST['username']
        password = request.POST['password']
        
        user = authenticate(username=username,password=password)
        
        if user is not None:
            login(request,user)
            
            token, _ = Token.objects.get_or_create(user=user)
            userdata = {
                'username' : user.username
            }
            return Response(userdata)
        else:
            return Response({'message':'invaild credentials'})
    else:
        Response({'messaage':'invaid request'})
        
@api_view(['POST'])
@permission_classes([AllowAny])
def create(request):
    if request.user.is_authenticated:
        if request.method=='POST':
            user = request.user
            works = request.POST['works']
            listed = listingmodels.objects.create(user=user,works=works)
            listed.save()
            userdata = {
                'works':listed.works,
            }
            
            return Response(userdata)
        return Response({'message':'invalid request'})
    return Response({'message':'not authenticated'})


@api_view(['GET'])
@permission_classes([AllowAny])
def getdata(request):
    if request.user.is_authenticated:
        user = request.user
        entries = listingmodels.objects.filter(user=user)
        serialized_entries = [{'works': entry.works} for entry in entries]
        return Response(serialized_entries)
    else:
        return Response({'message','user not authenticated'})
        
    
            