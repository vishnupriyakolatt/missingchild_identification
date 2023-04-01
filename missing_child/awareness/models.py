from django.db import models

# Create your models here.
class Awarness(models.Model):
    a_id = models.AutoField(primary_key=True)
    a_date = models.DateField()
    a_title = models.CharField(max_length=50)
    a_details = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'awarness'

