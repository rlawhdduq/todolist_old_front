package todolist.old_front.dto.board;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailDeleteDto {

    private Long foreign_key;
    private List<Long> key_list;
    
}
