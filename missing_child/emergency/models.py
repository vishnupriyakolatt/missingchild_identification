from django.db import models

# Create your models here.
class Emergency(models.Model):
    e_id = models.AutoField(primary_key=True)
    e_name = models.CharField(max_length=50)
    e_no = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'emergency'
