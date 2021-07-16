package cn.com.ktm.mt.model.sitestructure.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsSiteStuctureListRes extends CsSiteStuctureResponse {
    List<CsSiteStuctureResponseBody> csSiteStuctureResponseBodyList;
}
