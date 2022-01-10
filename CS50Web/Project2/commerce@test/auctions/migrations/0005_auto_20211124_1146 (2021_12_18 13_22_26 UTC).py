# Generated by Django 3.2.9 on 2021-11-24 06:16

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('auctions', '0004_alter_listings_date'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='listings',
            name='date',
        ),
        migrations.AddField(
            model_name='listings',
            name='listing_open_date',
            field=models.DateField(default=datetime.date(2021, 11, 24)),
        ),
    ]