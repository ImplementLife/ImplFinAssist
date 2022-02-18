using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using USER;

namespace API
{
    class Mock : API
    {     
            
        public bool removeUser(UserInfo userInfo)
        {
                return true;
        }

    }
}
