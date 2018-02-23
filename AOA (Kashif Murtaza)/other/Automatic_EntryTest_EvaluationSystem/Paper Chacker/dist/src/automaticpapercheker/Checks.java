package automaticpapercheker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TAYYAB
 */
public class Checks {
    public static boolean checkDateFormat(String day,String month,String year){
        int d=Integer.parseInt(day);
        if(d<1 || d>31){
            return false;
        }
        int m=Integer.parseInt(month);
        if(m<1 || m>12){
            return false;
        }
        int y=Integer.parseInt(year);
        if(y<1){
            return false;
        }
        return true;
    }
   public static boolean  check_alp(String str1)
        {
            char [] str = str1.toCharArray();
            int size = str.length;

            for (int i = 0; i < size; i++)
            {
                if ((str[i] < 65 || str[i] > 90) && (str[i] < 97 || str[i] > 122))
                {
                    if (str[i] != ' ' && str[i]!='\n')
                        return false;
                }
            }

            return true;
        }

       public static boolean check_dig(String str1)
        {
            char [] str = str1.toCharArray();
            int size = str.length;

            for (int i = 0; i < size; i++)
            {
                if (str[i] < 48 || str[i] > 57)
                {
                    if(str[i]!='\n')    
                    return false;
                }
            }

            return true;
        }


      public  static boolean check_price(String str1)
        {
              char [] str = str1.toCharArray();
            int size = str.length;
            int flag = 1;
            int flag1 = 0;
            for (int i = 0; i < size; i++)
            {
                flag = 0;
                if (str[i] < 48 || str[i] > 57)
                {
                    flag = 1;
                }
                if (flag == 1 && str[i] != '.' || flag1 >= 2)
                {
                    if(str[i]!='\n')    
                    return false;
                }
                else if (str[i] == '.')
                    flag1++;

            }

            return true;

        }

      public  static boolean check_size(String str1)
        {
          char [] str = str1.toCharArray();
            int size = str.length;

            for (int i = 0; i < size; i++)
            {
                if ((str[i] < 65 || str[i] > 90) && (str[i] < 97 || str[i] > 122) && (str[i] < 48 || str[i] > 57))
                {
                    if(str[i]!='.' && str[i]!=' ' && str[i]!='\n')
                    return false;
                }
            }

            return true;
        }

       public static boolean check_an(String str1)
        {
          char [] str = str1.toCharArray();
            int size = str.length;

            for (int i = 0; i < size; i++)
            {
                if ((str[i] < 65 || str[i] > 90) && (str[i] < 97 || str[i] > 122) && (str[i] < 48 || str[i] > 57))
                {
                    if(str[i]!='.' && str[i]!=' ' && str[i]!='\n')
                    return false;
                }
            }

            return true;
        }

}
