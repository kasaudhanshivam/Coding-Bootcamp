class Basics{
    // i = tree index
    // x = idx of original arr to be updated
    static int[] tree;
    static int[] arr;
    public static void init(int[] nums){
        int n = nums.length;
        tree = new int[2*n];
        arr = nums;
    }
    public static void buildTree(int i, int l, int r){
        if(l==r){
            tree[i] = arr[l];
            return;
        }

        int mid = l + (r-l)/2;
        buildTree((2*i)+1, l, mid);
        buildTree((2*i)+2, mid+1, r);

        tree[i] = tree[(2*i)+1] + tree[(2*i)+2];
    }
    public static void update(int x, int i, int val, int l, int r){
        if(l==r){
            tree[i] = val;
            return;
        }

        int mid = l + (r-l)/2;
        if(x<=mid){
            update(x, (2*i)+1, val, l, mid);
        }else{
            update(x, (2*i)+2, val, mid+1, r);
        }
        tree[i] = tree[(2*i)+1] + tree[(2*i)+2];
    }
    public static void main(String[] args) {
        int[] nums = {};
        init(nums);
        buildTree(0, 0, nums.length-1);
    }
}