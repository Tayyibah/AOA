% dirPath = 'C:\Users\KASHIF\Desktop\computerVision\ComputerVision_PUCIT_Fall_12\att_faces\';
% 
% numSubjects = 40;
% numImages = 10;
% ext = '.pgm';
% 
% A = [];
% labels = [];
% sz = [];
% for s = 1:numSubjects
%     for i = 1:numImages
%         imPath = [dirPath 's' num2str(s) '\' num2str(i) ext];
%         im = imread(imPath);
%         if size(im,3) == 3
%             im = rgb2gray(im);            
%         end
%         sz = size(im);
%         A = [A double(im(:))];            
%         labels = [labels s];
%     end
% end
% 
% save('faceData','A','labels');
% 
load faceData;
testIdx = ceil(length(labels)*rand);

testFace = A(:,testIdx);

testLabel = labels(:,testIdx);

A(:,testIdx) = [];
labels(testIdx) = [];

mA = mean(A,2);
mA_vec = mA;
mA = repmat(mA,1,size(A,2));
B = double(A)-mA;

% C = B'*B;
% [V D] = eig(C);
% V = B*V;
% 
% VNorms = sqrt(sum(V.^2,1));
% V = V./repmat(VNorms,size(V,1),1);


[U,S,V] = svd(B');  %% you can find out eigenVectors of B*B' by using SVD Also...
D = S.*S;           %% Eigen Values of B*B'...  

eVals = diag(D);

[eVals idx] = sort(eVals, 'descend');
V = V(:,idx);
k = f_numBasis(eVals,0.9);
V = V(:,1:k);
Ap = V'*B;

testFaceB = testFace-mA_vec;
testFaceP = V'*testFaceB;

totalSubjects = unique(labels);

mS = [];
for i = 1:length(totalSubjects)
   idxi = (labels == i);
   facesI = Ap(:,idxi);
   mFacesI = mean(facesI,2);
   mS = [mS mFacesI];
   % mS = [mS mean(Ap(:,labels==i),2)];
end

%d = [];
% for i = 1:size(mS,2)
%     mI = mS(:,i);
%     di = sqrt(sum((testFaceP-mI).^2));
%     d = [d di];
% end

testFaceM = repmat(testFaceP,1,size(mS,2));
d = sum((testFaceM-mS).^2,1).^0.5;
[minVal minIdx] = min(d);

disp(['The Face is of Subject ' num2str(minIdx)]);    
    
    
    
    

% A_dash = (V*Ap)+mA;
% 
% reconError = sqrt(sum(sum((A-A_dash).^2)));
% disp(reconError);
% 
% 
% 
% 
% 
% 
% 
% 
% 
