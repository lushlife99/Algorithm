import numpy as np
import json

def solution(arr1, arr2):
    result = np.dot(arr1, arr2)
    return result.tolist()