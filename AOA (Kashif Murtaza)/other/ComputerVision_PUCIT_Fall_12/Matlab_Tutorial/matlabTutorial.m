% A = ceil(10*rand(6,7));
% 
% B = zeros(size(A));
% 
% %B(1,:) = A(6,:);
% %B(2,:) = A(5,:);
% 
% for r = 1:size(A,1)
%    B(r,:) = A(end-r+1,:);     
% end
% 
% C = zeros(size(B));
% 
% for c = 1:size(B,2)
%    C(:,c) = B(:,end-c+1);     
% end
% 
% disp(A);
% disp(B);
% disp(C);

a = rand(1,10);
s = 0;
for i = 1:length(a)
   s = s+a(i); 
end
disp('The sum of all the numbers is...');
disp(s);