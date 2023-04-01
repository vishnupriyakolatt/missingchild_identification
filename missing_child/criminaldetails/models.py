from django.db import models
from policestation.models import Policestation
# Create your models here.
class CriminalDetails(models.Model):
    cr_id = models.AutoField(primary_key=True)
    cr_date = models.DateField()
    cr_name = models.CharField(max_length=50)
    cr_age = models.IntegerField()
    personal_details = models.CharField(max_length=50)
    case_details = models.CharField(max_length=100)
    cr_img = models.CharField(max_length=100)
    # p_id = models.IntegerField()
    p = models.ForeignKey(Policestation, to_field='p_id', on_delete=models.CASCADE)
    class Meta:
        managed = False
        db_table = 'criminal_details'
