from django.shortcuts import render
from login.models import Login
# from login.views import login
from user.models import User
from user.serializer import android
from  rest_framework.views import APIView,Response
from django.http import HttpResponse

# Create your views here.

def viewuser(request):
    o=User.objects.all()
    context={
        'objval':o,
    }
    return render(request, 'user/viewuser.html',context)


class user(APIView):
    def get(self,request):
        obj = User.objects.all()
        ser = android(obj,many=True)
        return Response(ser.data)


    def post(self,request):
        obj=User()
        obj.username=request.data['username']
        obj.u_gender=request.data['u_gender']
        obj.u_address= request.data['u_address']
        obj.u_email = request.data['u_email']
        obj.u_phno = request.data['u_phno']
        obj.password= request.data['password']
        obj.u_pin= request.data['u_pin']
        obj.u_state= request.data['u_state']
        obj.u_country = request.data['u_country']
        obj.save()
        ob=Login()
        ob.username = request.data['u_email']
        ob.password = request.data['password']
        ob.type = "user"
        ob.user_id=obj.user_id
        ob.save()

        return HttpResponse("yesss")