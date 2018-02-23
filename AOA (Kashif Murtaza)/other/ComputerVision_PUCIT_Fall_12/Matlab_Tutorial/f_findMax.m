function mx = f_findMax(A)

    if (isempty(A))
%        disp('Give good matrix...');
%        return;

        error('Matrix is empty...');
    end
    
    mx = -inf; %% mx = min(min(A));
    for r = 1:size(A,1)
        for c = 1:size(A,2)
            if(A(r,c)> mx)
               mx = A(r,c); 
            end
        end
    end
    
    %return mx;

end