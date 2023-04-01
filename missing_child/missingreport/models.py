from django.db import models
from user.models import User

# Create your models here.
class ReportMissingcase(models.Model):
    report_id = models.AutoField(primary_key=True)
    report_date = models.DateField()
    report_time = models.TimeField()
    report_loc = models.CharField(max_length=50)
    report_img = models.CharField(max_length=150)
    # user_id = models.IntegerField()
    user = models.ForeignKey(User, to_field='user_id', on_delete=models.CASCADE)

    class Meta:
        managed = False
        db_table = 'report_missingcase'

