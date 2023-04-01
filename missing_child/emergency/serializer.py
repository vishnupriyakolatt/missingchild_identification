from rest_framework import serializers
from emergency.models import Emergency
class android(serializers.ModelSerializer):
    class Meta:
        model=Emergency
        fields='__all__'
