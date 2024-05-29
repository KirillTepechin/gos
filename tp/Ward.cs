using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PodgotovkaTP
{
    public class Ward
    {
        public int Id { get; set; }
        public string Name { get; set; }

        [ForeignKey("WardNumber")]
        public virtual List<Patient> Patients { get; set; }
    }
}
