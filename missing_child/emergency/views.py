from django.shortcuts import render
from emergency.models import Emergency
from django.http import HttpResponse

from emergency.serializer import android
from  rest_framework.views import APIView,Response

def add_emergencyno(request):
    if request.method=="POST":
        obj=Emergency()
        obj.e_name=request.POST.get('emergencyno')
        obj.e_no=request.POST.get('phno')
        obj.save()
    return render(request,'emergency/addemergencyno.html')

class view_emergency(APIView):
    def get(self,request):
        obj =Emergency.objects.all()
        ser = android(obj,many=True)
        return Response(ser.data)


    def post(self,request):
        obj=Emergency()
        obj.e_name=request.data['e_name']
        obj.e_no=request.data['e_no']
        obj.save()
        return HttpResponse("yesss")