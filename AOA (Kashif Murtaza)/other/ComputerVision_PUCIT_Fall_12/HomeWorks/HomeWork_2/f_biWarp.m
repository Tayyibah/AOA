function im2 = f_biWarp(H,im)

%% projective warping for grayscale images only.. slow code for understanding of the students.. 
%% alot of loops making the code slow, one can use meshgrid instead and a fency way of bilinear
%% interpoloation (clever matrix indexing) to avoid all the loops

    %A = [v(1) v(2) v(3); v(4) v(5) v(6); 0 0 1];
    if det(H) == 0
        error('wrong homography...');
    end
    
    extents = H*[1 1 1 ; 1 size(im,2) 1; size(im,1) 1 1; size(im,1) size(im,2) 1]';
    extents = extents ./ [extents(3,:) ; extents(3,:); extents(3,:)];  % in homoginizing...
    
    minExtents = floor(min(extents,[],2));
    maxExtents = ceil(max(extents,[],2));
    
                
    im2 = zeros(maxExtents(1)-minExtents(1)+1 , maxExtents(2)-minExtents(2)+1);
    
    invH = inv(H);
    
    for r = minExtents(1):maxExtents(1)
        for c = minExtents(2):maxExtents(2)
            b = [r;c;1];
            x = invH*b;
            x = x ./ [x(3); x(3); x(3)]; %% in homoginizing...
            
            if x(1)>=1 && x(1)<=size(im,1) && ...
                    x(2) >= 1 && x(2) <= size(im,2)                
                   im2(r-minExtents(1)+1 , c-minExtents(2)+1) = f_simpleRounding(x,im);
                   %im2(r-minExtents(1)+1 , c-minExtents(2)+1) = f_bilinear(x,im);                                                                     
            end            
            
        end
    end
    
end

function val = f_simpleRounding(x,im)
    x = round(x);
    val = im(x(1),x(2));
end


function val = f_bilinear(p,im)
                    
    x = p(1); y = p(2);
    
    eXc = ceil(x)-x;
    eYc = ceil(y)-y;
    eXf = x - floor(x);
    eYf = y - floor(y);
    
%     if x == ceil(x) && y == ceil(y)
%        val = im(x,y); 
%        return;
%     end
    
    if x == ceil(x)  % x is integer...
        eXf = 1;
        eXc = 0;               
    end            
    
    if y == ceil(y) % y is integer....
       eYf = 1;
       eYc = 0;                
    end

    val =      eXc*eYc * im(floor(x),floor(y)) + ...
               eXf*eYc * im(ceil(x),floor(y)) + ...
               eXc*eYf * im(floor(x),ceil(y)) + ...
               eXf*eYf * im(ceil(x),ceil(y));  
           
end