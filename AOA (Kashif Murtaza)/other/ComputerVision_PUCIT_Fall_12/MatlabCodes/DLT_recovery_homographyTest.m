clc;
doNoise = false;
noiseStrength = 1;


%%% least squares
H = ceil(10*rand(3));
nPts = 400;
p = 10*rand(3,nPts);
p(3,:) = 1;
p_dash = H*p;

if doNoise
   p_dash = p_dash + noiseStrength*randn(size(p_dash)); 
end

p_dash2 = p_dash./[p_dash(3,:); p_dash(3,:); p_dash(3,:)];


A = zeros(3*nPts,9);
p2 = p';

A(1:3:end,1:3) = p2;
A(2:3:end,4:6) = p2;
A(3:3:end,7:9) = p2;

b = p_dash2(:);

h = inv(A'*A)*A'*b;

H_recovered = reshape(h,3,3)';

if doNoise
    disp('With noisy markings...');
else
    disp('Ideal markings without noise...')
end

disp('Recovered by applying least square error solution like Affine recovery')
%disp(h');
disp(H_recovered);

%%% DLT 

p_dash2T = p_dash2';
A2 = zeros(2*nPts,9);
A2(1:2:end,4:6) = -p2;
A2(2:2:end,1:3) = p2;
A2(1:2:end,7:9) = repmat(p_dash2T(:,2),1,3).*p2;
A2(2:2:end,7:9) = repmat(-p_dash2T(:,1),1,3).*p2;

h2 = null(A2);
if isempty(h2)
    disp('Null space is empty....');
else
    H_recovered2 = reshape(h2,3,3)';

%disp(h2');
    disp('Recovered by DLT')
    disp(H_recovered2/sqrt(sum(sum(H_recovered2.^2))));
end

disp('Ground Truth')
disp(H/sqrt(sum(sum(H.^2))));

[a b] = eig(A2'*A2);  

disp('Eigen Vector of (A_transpose * A) corresponding to least eigen value');
disp(reshape(a(:,1),3,3)'); %% asuming sorted eigen values... mathworks however does not gaurentee it...
