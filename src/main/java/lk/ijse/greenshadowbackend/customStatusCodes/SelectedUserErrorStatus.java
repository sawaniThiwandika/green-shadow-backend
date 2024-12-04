package lk.ijse.greenshadowbackend.customStatusCodes;


import lk.ijse.greenshadowbackend.dto.impl.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserErrorStatus implements UserStatus {
    private int status;
    private String statusMessage;

}
