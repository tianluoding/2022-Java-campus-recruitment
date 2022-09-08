import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main1 {
    public static int subarr(int[] a, int[] b) {
        int cnt = 0;
        for(int i = 0; i < 26; i ++) {
            a[i] = a[i] - b[i];
            if(a[i] < 0) cnt -= a[i];
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        char[] t = sc.next().toCharArray();


        int[] mp_s = new int[26];
        int[] mp_t = new int[26];
        int star_cnt = 0;
        for(char i : s) {
            if(i < 'a' || i > 'z'){
                star_cnt++;
            }else{
                mp_s[i-'a']++;
            }
        }
        int tmp_star_cnt = star_cnt;
        for(char i : t) {
            mp_t[i-'a']++;
        }
        List<Character> res = new ArrayList<>();
        while(star_cnt > 0) {
            int tmp = subarr(mp_s, mp_t);
            if(star_cnt < tmp) break;
            star_cnt -= tmp;
            for(int i = 0; i < 26; i ++) {
                if(mp_s[i] < 0) {
                    for(int j = 0; j < -mp_s[i]; j ++) res.add(new Character((char)('a' + i)));
                    mp_s[i] = 0;
                }
            }
        }

        int j = 0;
        while(res.size() < tmp_star_cnt) {
            res.add('a');
        }
        res.sort(Comparator.reverseOrder());
        for(int i = 0; i < s.length; i ++) {
            if(j < res.size() && s[i] == '#'){
                System.out.print(res.get(j++));
            }else if(j >= res.size() && s[i] == '#'){
                System.out.print('a');
            }else{              
                System.out.print(s[i]);
            }
        }

    }
}
