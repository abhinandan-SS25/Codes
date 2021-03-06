# Generated by Django 3.2.9 on 2021-11-25 15:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('auctions', '0005_auto_20211125_2106'),
    ]

    operations = [
        migrations.AddField(
            model_name='listing',
            name='c_bidder',
            field=models.CharField(default=models.CharField(max_length=80), max_length=80),
        ),
        migrations.AlterField(
            model_name='listing',
            name='c_price',
            field=models.FloatField(default=0.0),
        ),
        migrations.DeleteModel(
            name='Bid',
        ),
    ]
