from django.shortcuts import render
from complaint.models import Complaint
from django.http import HttpResponse
from complaint.serializer import android
from rest_framework.views import APIView,Response

def view_complaint(request):
    o=Complaint.objects.all()
    context={
        'objval':o,
    }
    return render(request, 'complaint/viewcomplaint.html',context)


def reply_post(requset,abc):
    if requset.method=="POST":
        obj=Complaint.objects.get(c_id=abc)
        obj.c_reply=requset.POST.get('rply')
        obj.save()
    # o=Complaint.objects.all()
    return render(requset,'complaint/replypost.html')
import datetime
class post_complaint(APIView):
    def get(self,request):
        obj = Complaint.objects.all()
        ser = android(obj,many=True)
        return Response(ser.data)

    def post(self,request):
        obj=Complaint()
        obj.c_date=datetime.datetime.today()
        obj.time=datetime.datetime.now().time()
        obj.complaint= request.data['complaint']
        obj.c_reply= "pending"
        obj.user_id= request.data['user_id']
        obj.save()
