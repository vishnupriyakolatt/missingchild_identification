from rest_framework import serializers
from login.models import Login
class android(serializers.ModelSerializer):
    class Meta:
        model=Login
        fields='__all__'

