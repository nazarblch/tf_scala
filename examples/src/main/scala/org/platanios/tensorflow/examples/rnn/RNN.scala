package org.platanios.tensorflow.examples.rnn

import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.ops.rnn.cell.{RNNCell, Tuple}

class RNNResult[S](val outputs: Seq[Output], val finalState: S)

class RNN[S, SS](cell: RNNCell[Output, Shape, S, SS], init_state: S)  {
   def forward(inputs_series: Seq[Output]): RNNResult[S] = {
     var current_state = init_state
     val outputs = inputs_series.map(cur_input => {
       val os = cell.forward(Tuple(cur_input, current_state))
       current_state = os.state
       os.output
     })

     new RNNResult(outputs, current_state)
   }
}

object RNN {
  def apply[S, SS](cell: RNNCell[Output, Shape, Seq[S], Seq[SS]], common_init_state: S, cells_count: Int): RNN[Seq[S], Seq[SS]] = {
    new RNN(cell, Seq.fill(cells_count)(common_init_state))
  }
}
