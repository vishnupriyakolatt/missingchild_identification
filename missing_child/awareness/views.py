from django.shortcuts import render
from awareness.models import Awarness
from django.http import HttpResponse

from awareness.serializer import android
from  rest_framework.views import APIView,Response
# Create your views here.


def add_awareness(request):
    if request.method=="POST":
        obj=Awarness()
        obj.a_date=request.POST.get('date')
        obj.a_details=request.POST.get('des')
        obj.a_title=request.POST.get('title')
        obj.save()
    return render(request,'awareness/addawarness.html')


class awareness_view(APIView):
    def get(self,request):
        obj = Awarness.objects.all()
        ser = android(obj,many=True)
        return Response(ser.data)


    def post(self,request):
        obj=Awarness()
        obj.a_date=request.data['a_date']
        obj.a_title=request.data['a_title']
        obj.a_details= request.data['a_details']
        obj.save()
        return HttpResponse("yesss")