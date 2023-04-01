from django.shortcuts import render
from criminaldetails.models import CriminalDetails
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse
from django.http import HttpResponseRedirect
from django.db import connection
from django.http import JsonResponse

from criminaldetails.serializer import android
from  rest_framework.views import APIView,Response

def criminaldetails_manage(request):
    dd=request.session["uid"]
    if request.method=="POST":
        obj=CriminalDetails()
        obj.cr_date=request.POST.get('date')
        obj.cr_name=request.POST.get('name')
        obj.cr_age= request.POST.get('age')
        obj.personal_details=request.POST.get('person')
        obj.case_details= request.POST.get('case')
        img = request.FILES['img']
        ff = FileSystemStorage()
        filename = ff.save(img.name, img)
        obj.cr_img= img.name
        obj.p_id=dd
        obj.save()
    return render(request, 'criminaldetails/criminaldetailsmanage.html')
def view_criminal(request):
    o=CriminalDetails.objects.all()
    context={
        'objval':o,
    }
    return render(request,'criminaldetails/viewcriminaldetails.html',context)

def update_criminal(request,idd):
    o=CriminalDetails.objects.filter(cr_id=idd)
    context={
        'objval':o,
    }
    if request.method=="POST":
        obj=CriminalDetails.objects.get(cr_id=idd)
        obj.cr_date = request.POST.get('date')
        obj.cr_name = request.POST.get('name')
        obj.cr_age = request.POST.get('age')
        obj.personal_details = request.POST.get('person')
        obj.case_details = request.POST.get('case')
        img = request.FILES['img']
        ff = FileSystemStorage()
        filename = ff.save(img.name, img)
        obj.cr_img = img.name
        obj.p_id =idd
        obj.save()



    return render(request,'criminaldetails/updatecriminaldetails.html',context)



def delete_criminal(request,idd):
    print(idd)
    o=CriminalDetails.objects.get(cr_id=idd).delete()
    # return render(request,'criminaldetails/viewcriminaldetails.html')
    return HttpResponseRedirect('/criminaldetails/viewcriminaldetails/')


class view_criminaldetails(APIView):
    def get(self,request):
        cursor=connection.cursor()
        cursor.execute(
            "SELECT * FROM policestation,criminal_details WHERE policestation.p_id=criminal_details.p_id")
        allv=cursor.fetchall()
        json_data=[]
        for obj in allv:
            json_data.append(
                {"date":obj[11],"name":obj[12],"age":obj[13],"personal":obj[14],"case_details":obj[15],"station":obj[1],"cr_img":obj[16]}
            )
        return JsonResponse(json_data,safe=False)

    def post(self,request):
        obj=CriminalDetails()
        obj.cr_date=request.data['cr_date']
        obj.personal_details= request.data['personal_details']
        obj.case_details= request.data['case_details']
        obj.cr_img= request.data['cr_img']
        obj.p_id= request.data['p']
        obj.save()
        return HttpResponse("yesss")