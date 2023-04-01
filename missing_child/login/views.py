from django.shortcuts import render
from login.models import Login
from user.models import User
from django.http import HttpResponse,HttpResponseRedirect
from policestation.models import Policestation


from login.serializer import android
from  rest_framework.views import APIView,Response
from django.contrib import messages

# Create your views here.
def login(request):
    if request.method == "POST":
        uname = request.POST.get("uname")
        passw = request.POST.get("password")
        if Login.objects.filter(username=uname, password=passw).exists():
            messages.info(request, "Already Exist")
        else:
            obj = Login.objects.filter(username=uname, password=passw)
            tp = ""
            for ob in obj:
                tp = ob.type
                uid = ob.user_id
                if tp == "police":
                    request.session["uid"] = uid
        return render(request, 'temp/policehome.html')




            # else:
            #     objlist = "Username or Password incorrect... Please try again...!"
            #     context = {
            #         'msg': objlist,
            #     }
            #     return render(request, 'login/login.html', context)
    return render(request,'login/login.html')

class login1(APIView):
    def get(self,request):
        obj =Login.objects.all()
        ser = android(obj,many=True)
        return Response(ser.data)

    def post(self, request):
        unm = request.data['username']
        pwd = request.data['password']
        ob = Login.objects.filter(username=unm, password=pwd)
        # print(ob)
        ser = android(ob, many=True)
        return Response(ser.data)


class forgot(APIView):
    def get(self, request):
        ob = Login.objects.all()
        ser = android(ob, many=True)
        return Response(ser.data)

    def post(self, request):
        ps = request.data['password']
        mail = request.data['u_email']

        if User.objects.filter(u_email=mail).exists():
            obj = User.objects.get(u_email=mail)
            print(obj)
            obj.password=ps
            obj.save()
            ob = Login.objects.get(username=mail)
            ob.password = ps
            ob.save()
            return HttpResponse("Success")
        else:
            return HttpResponse("invalid email")
        # obj.save()
        # ser = android(ob, many=True)
        # return Response(ser.data)
        # return HttpResponse("yessss")
def forgot1(request):
    if request.method == "POST":

        email= request.POST.get("uname")
        passw = request.POST.get("password")
        if Login.objects.filter(username=email).exists():


            obj=Policestation.objects.get(p_email=email)
            obj.password=passw
            obj.save()

            obb=Login.objects.get(username=email)
            obb.password=passw
            obb.save()
            return HttpResponseRedirect('/login/login/')
        else:

            msg="invalid email"
            context={
                 'inv':msg
            }
            return render(request, 'login/forgot.html',context)
    return render(request,"login/forgot.html")