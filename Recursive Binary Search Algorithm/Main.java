// Kevin Pulikkottil
// 2/23/23
// JDK version 14.0.1
// this program is a recursive implementation of the binary search algorithm which handles sorted arrays of integers that can contain duplicates
public class Main {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, 2, 5 };
        int key = 2;
        int[] result = binarySearch(nums, key);
        System.out.println("Return =" + " [" + result[0] + ", " + result[1] + "]");
        key = 3;
        result = binarySearch(nums, key);
        System.out.println("Return =" + " [" + result[0] + ", " + result[1] + "]");
        int[] nums2 = { 1, 2, 5 };
        key = 2;
        result = binarySearch(nums2, key);
        System.out.println("Return =" + " [" + result[0] + ", " + result[1] + "]");

        key = 3;
        result = binarySearch(nums2, key);
        System.out.println("Return =" + " [" + result[0] + ", " + result[1] + "]");
    }

    public static int[] binarySearch(int[] nums, int key) {
        return binarySearchRecursive(nums, key, 0, nums.length - 1);
    }

    private static int[] binarySearchRecursive(int[] nums, int key, int low, int high) {
        if (low > high) {
            // if low > high the key is not present in array
            return new int[] { -1, -1 };
        }
        int mid = low + (high - low) / 2;

        if (nums[mid] < key) {
            // if nums[mid] < key, search the right half of the array
            return binarySearchRecursive(nums, key, mid + 1, high);
        } else if (nums[mid] > key) {
            // if nums[mid] > key, search the left half of the array
            return binarySearchRecursive(nums, key, low, mid - 1);
        } else {
            // if nums[mid] == key, find the start and end indices of the key in the array
            int startIndex = mid;
            int endIndex = mid;

            // find the start index of the key in the array
            int[] leftResult = binarySearchRecursive(nums, key, low, mid - 1);
            if (leftResult[0] != -1) {
                startIndex = leftResult[0];
            }

            // find the end index of the key in the array
            int[] rightResult = binarySearchRecursive(nums, key, mid + 1, high);
            if (rightResult[1] != -1) {
                endIndex = rightResult[1];
            }

            return new int[] { startIndex, endIndex };
        }
    }

}