dirPath = 'C:\Users\KASHIF\Desktop\computerVision\ComputerVision_PUCIT_Fall_12\att_faces\';

numSubjects = 40;
numImages = 10;
ext = '.pgm';

A = [];
sz = [];
for s = 1:numSubjects
    for i = 1:numImages
        imPath = [dirPath 's' num2str(s) '\' num2str(i) ext];
        im = imread(imPath);
        if size(im,3) == 3
            im = rgb2gray(im);
            sz = size(im);
        end
        A = [A double(im(:))];            
    end
end

mA = mean(A,2);
mA = repmat(mA,1,size(A,2));
B = double(A)-mA;
C = B'*B;
[V D] = eig(C);
V = B*V;
eVals = diag(D);

[eVals idx] = sort(eVals, 'descend');
V = V(:,idx);
V = V(:,1:10);
Ap = V'*B;

A_dash = (V*Ap)+mA;

reconError = sum(sum((A-A_dash).^2));
disp(reconError);









