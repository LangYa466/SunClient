import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

import net.minecraft.client.main.Main;

public class Start
{
    public static void main(String[] args)
    {
        Main.main(concat(new String[] {"--username","Nel1y_1337","--uuid","99246980df8045e9a80fa5229d9ed160","--version", "mcp", "--accessToken","eyJraWQiOiJhYzg0YSIsImFsZyI6IkhTMjU2In0.eyJ4dWlkIjoiMjUzNTQ1MzkzNTQyNzgzNSIsImFnZyI6IkFkdWx0Iiwic3ViIjoiYzkxMzc0NTMtNjRiNS00M2MwLThmNzYtNzIzMmQwYzg1MzA2IiwiYXV0aCI6IlhCT1giLCJucyI6ImRlZmF1bHQiLCJyb2xlcyI6W10sImlzcyI6ImF1dGhlbnRpY2F0aW9uIiwiZmxhZ3MiOlsidHdvZmFjdG9yYXV0aCIsIm1zYW1pZ3JhdGlvbl9zdGFnZTQiLCJvcmRlcnNfMjAyMiIsIm11bHRpcGxheWVyIl0sInByb2ZpbGVzIjp7Im1jIjoiOTkyNDY5ODAtZGY4MC00NWU5LWE4MGYtYTUyMjlkOWVkMTYwIn0sInBsYXRmb3JtIjoiVU5LTk9XTiIsInl1aWQiOiJlZjlhZDdkY2M1NmU3Y2NiYjk3NWYzNzI0OGU3OWFmOCIsIm5iZiI6MTcwNTU3OTAyMiwiZXhwIjoxNzA1NjY1NDIyLCJpYXQiOjE3MDU1NzkwMjJ9.7mTYg-90fKJo0kjWGWdXEtWxEWOPYVAFFZCfEjuLHoE", "0", "--assetsDir", "assets", "--assetIndex", "1.12", "--userProperties", "{}"}, args));
    }

    public static <T> T[] concat(T[] first, T[] second)
    {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
