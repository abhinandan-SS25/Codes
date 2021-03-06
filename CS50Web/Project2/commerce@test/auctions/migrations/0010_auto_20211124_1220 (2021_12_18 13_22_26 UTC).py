# Generated by Django 3.2.9 on 2021-11-24 06:50

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('auctions', '0009_auto_20211124_1219'),
    ]

    operations = [
        migrations.AlterField(
            model_name='categories',
            name='category',
            field=models.CharField(max_length=60),
        ),
        migrations.AlterField(
            model_name='listings',
            name='category',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='Type', to='auctions.categories'),
        ),
    ]
