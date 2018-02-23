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
            
        end
        sz = size(im);
        A = [A double(im(:))];            
    end
end


mA = mean(A,2);
mA = repmat(mA,1,size(A,2));
B = double(A)-mA;

C = B'*B;
[V D] = eig(C);
V = B*V;

VNorms = sqrt(sum(V.^2,1));
V = V./repmat(VNorms,size(V,1),1);


% [U,S,V] = svd(B');  %% you can find out eigenVectors of B*B' by using SVD Also...
% D = S.*S;           %% Eigen Values of B*B'...  

eVals = diag(D);

[eVals idx] = sort(eVals, 'descend');
V = V(:,idx);
V = V(:,1:10);
Ap = V'*B;  %% projection

A_dash = (V*Ap)+mA;  % back projection

reconError = sqrt(sum(sum((A-A_dash).^2)));
disp(reconError);









