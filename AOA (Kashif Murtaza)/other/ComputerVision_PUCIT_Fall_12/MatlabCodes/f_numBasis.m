function k = f_numBasis(eVals,energyVal)
    totalSum = sum(eVals);
    for k = 1:length(eVals)
        if sum(eVals(1:k))/totalSum >= energyVal
            break;
        end
    end        
end