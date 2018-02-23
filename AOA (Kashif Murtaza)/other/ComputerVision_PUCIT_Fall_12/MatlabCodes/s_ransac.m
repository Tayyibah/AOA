im = imread('C:\Users\KASHIF\Desktop\computerVision\ComputerVision\Vision_work\homework_3b\dataset\dataset\type2cards\scanned6.tif');
if size(im,3) == 3
    im = rgb2gray(im);
end

e = edge(im,'canny');

[X Y] = find(e==1);

numIter = 1000;
consistencyTh = 4;

goodM = 0; goodC = 0; goodS = 0;

for iter = 1:numIter
    
    while true        
        idx1 = ceil(rand*length(X));
        idx2 = ceil(rand*length(X));
        if ~(idx1 == 0 || idx2 == 0 || (idx1 == idx2))
            break;
        end
    end
    
    x1 = X(idx1); y1 = Y(idx1); x2 = X(idx2); y2 = Y(idx2);
    
    if x2-x1 == 0
         for i = length(X)
            
            x = X(i); y = Y(i);
            
            d = abs(x-x1);
            if d <= consistencyTh
                tempS = tempS+1;
            end            
        end
        
        if tempS > goodS
            goodM = m;
            goodC = c;
            goodS = tempS;
        end
             
        
    else
        m = (y2-y1)/(x2-x1);
        c = -m*x1 + y1;
        tempS = 0;
        for i = 1:length(X)
           
            x = X(i); y = Y(i);
            
            d = abs(y-m*x-c)/sqrt(m^2+1);
            if d <= consistencyTh
                tempS = tempS+1;
            end            
        end
        
        if tempS > goodS
            goodM = m;
            goodC = c;
            goodS = tempS;
        end
                
    end
    
end
