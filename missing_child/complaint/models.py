from django.db import models
from user.models import User

# Create your models here.
class Complaint(models.Model):
    c_id = models.AutoField(primary_key=True)
    c_date = models.DateField()
    time = models.TimeField()
    complaint = models.CharField(max_length=50)
    c_reply = models.CharField(max_length=50)
    # user_id = models.IntegerField()
    user=models.ForeignKey(User,to_field='user_id',on_delete=models.CASCADE)
    class Meta:
        managed = False
        db_table = 'complaint'
