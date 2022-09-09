import os
os.environ['GOOGLE_APPLICATION_CREDENTIALS'] ='vision_key.json'
from google.cloud import vision
import re

vision_client = vision.ImageAnnotatorClient()
image = vision.Image()


image.source.image_uri = ('AZ-500.jpg')

response = vision_client.text_detection(image=image)

text = response.text_annotations[0].description

imeis = re.findall('[0-9]{14,15}', text)

print(imeis)