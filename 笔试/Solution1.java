import java.util.*;;

public class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<Double> a = new ArrayList<>();
        for(int i = 0; i < n; i++ ) a.add(sc.nextDouble());
        a.sort(Comparator.naturalOrder());
        if(k == n) {
            System.out.println(0);
            return;
        }
        if(k%2 == 0) {
            double s = 0;
            for(int i = 0, j = a.size()-1; i < k/2; i ++, j--) {
                s += (a.get(i) + a.get(j));
            }
            s /= k;
            double m_min = a.get(k/2);
            double m_max = a.get(a.size()-k/2-1);
            if(m_min > s) m_min = s;
            if(m_max < s) m_max = s;
            System.out.println(m_max-m_min);
        }else{
            double s1 = 0;
            double s2 = 0;
            for(int i = 0, j = a.size()-1; i < k/2; i ++, j --) {
                s1 += (a.get(i) + a.get(j));
                s2 += (a.get(i) + a.get(j));
            }
            s1 += a.get(k/2);
            s2 += a.get(a.size()-k/2-1);

            s1 /= k;
            s2 /= k;
            double m_min1 = a.get(k/2+1);
            double m_max1 = a.get(a.size()-k/2-1);

            double m_min2 = a.get(k/2);
            double m_max2 = a.get(a.size()-k/2-2);
            if(m_min1 > s1) m_min1 = s1;
            if(m_max1 < s1) m_max1 = s1;

            if(m_min2 > s2) m_min2 = s2;
            if(m_max2 < s2) m_max2 = s2;

            if(m_max1 - m_min1 > m_max2 - m_min2) {
                System.out.println(m_max1 - m_min1);
            }else{
                System.out.println(m_max2 - m_min2);
            }
        }
    }
}
