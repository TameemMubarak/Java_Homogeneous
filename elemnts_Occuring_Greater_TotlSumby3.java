import java.util.*;

class Solution {
    // Function to find the majority elements in the array
    public List<Integer> findMajority(int[] nums) {
        // Your code goes here.
        Arrays.sort(nums);
        int sum=1;
        List<Integer> cand=new ArrayList<Integer>();
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[i-1]){
                sum=sum+nums[i];
            }
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[i-1] &&nums[i]>sum/3){
                cand.add(nums[i]);
            }
            if(nums[0]>sum/3 && nums[0]!=nums[1]){
                cand.add(nums[0]);
            }
        }
        return cand;
    }
}
