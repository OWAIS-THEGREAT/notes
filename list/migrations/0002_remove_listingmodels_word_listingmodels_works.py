# Generated by Django 4.2.2 on 2023-07-09 18:02

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('list', '0001_initial'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='listingmodels',
            name='word',
        ),
        migrations.AddField(
            model_name='listingmodels',
            name='works',
            field=models.CharField(default='', max_length=50),
        ),
    ]