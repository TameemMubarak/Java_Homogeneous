class Dumy{
    public static void main(String[] args){
    int arr []={0,1,2,1,0,2};
                int zero=0;
        int one=0;
        int two=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0) {zero++;}
            if(arr[i]==1) {one++;}
            if(arr[i]==2) {two++;}
        }
        
        for(int j=0;j<arr.length;j++){
            if(zero!=0){
                arr[j]=0;
                --zero;
            }
            if(one!=0 && zero==0){
                arr[j]=1;
                --one;
            }
            if(two!=0 && one==0 ){
                arr[j]=2;
                --two;
            }
        }
    }
}