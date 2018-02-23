function AM = f_recoverAffine(X,X_dash)

    nPts = size(X,2);
    A = zeros(2*nPts,6);
    
    A(1:2:end,[1 2]) = X';
%     X2 = X';
%     for r = 1:2:size(A,1)
%        A(r,[1 2]) = X2(r,:); 
%     end

    A(1:2:end,3) = 1;
    A(2:2:end,[4 5]) = X';
    A(2:2:end,6) = 1;
    
    b = X_dash(:);
    
    x = inv(A'*A)*A'*b;
    
    AM = [x(1) x(2) x(3); x(4) x(5) x(6); 0 0 1];
    
end