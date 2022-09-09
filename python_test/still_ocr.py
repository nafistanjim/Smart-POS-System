from pytesseract import pytesseract
import cv2
import numpy as np
import matplotlib.pyplot as plt

#pytesseract.pytesseract.tesseract_cmd = r'C:\\Program Files\\Tesseract-OCR\\tesseract.exe'

path_to_tesseract = "C:\\Program Files\\Tesseract-OCR\\tesseract.exe"

pytesseract.tesseract_cmd=path_to_tesseract

font_scale = 1.5

img = cv2.imread('AZ-500.jpg')

imgchar = pytesseract.image_to_string(img)

print(imgchar)

imgboxes = pytesseract.image_to_boxes(img)

imgH, imgW,_ = img.shape
for boxes in imgboxes.splitlines():
    boxes = boxes.split(' ')
    x, y, w, h = int(boxes[1]), int(boxes[2]), int(boxes[3]), int(boxes[4])
    cv2.rectangle(img, (x, imgH-y),(w,imgH-h),(0,0,255),3) 

print(imgchar)
plt.imshow(img)
