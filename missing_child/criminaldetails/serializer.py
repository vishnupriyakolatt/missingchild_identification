from rest_framework import serializers
from criminaldetails.models import CriminalDetails
class android(serializers.ModelSerializer):
    class Meta:
        model=CriminalDetails
        fields='__all__'

