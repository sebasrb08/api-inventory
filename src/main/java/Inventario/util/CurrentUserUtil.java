package Inventario.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class CurrentUserUtil {
    public long getCurrentId() {
        return (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
