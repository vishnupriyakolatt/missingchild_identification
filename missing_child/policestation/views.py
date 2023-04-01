from django.shortcuts import render
from complaint.models import Complaint
from policestation.models import Policestation
from login.models import Login
from django.core.files.storage import FileSystemStorage
def register_police(request):
    if request.method=="POST":
        obj=Policestation()
        obj.p_name=request.POST.get('name')
        obj.p_address=request.POST.get('address')
        obj.p_pin = request.POST.get('pin')
        obj.p_state= request.POST.get('state')
        obj.p_country=request.POST.get('coun')
        obj.phone_no= request.POST.get('ph')
        obj.p_email= request.POST.get('email')
        # obj.password = request.POST.get('pass')

        img = request.FILES['img']
        ff = FileSystemStorage()
        filename = ff.save(img.name, img)
        obj.p_img = img.name

        # obj.p_img = request.POST.get('img')
        obj.password= request.POST.get('pass')
        obj.save()

        ob=Login()
        ob.username=request.POST.get('email')
        ob.password=request.POST.get('pass')
        ob.type="police"
        ob.user_id=obj.p_id
        ob.save()
    return render(request,'policestation/registrationform.html')
