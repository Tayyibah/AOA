im = rgb2gray(imread('C:\Users\KASHIF\Desktop\computerVision\ComputerVision_PUCIT_Fall_12\Lecture_1\images\stock-photo-2200838-iris-recognition.jpg'));

%C = [im im im ; im im im];
C = repmat(im,5,10);

imagesc(C),colormap gray;


